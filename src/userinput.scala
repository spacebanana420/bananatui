package bananatui

import java.io.File
import scala.sys.process.*
import scala.io.StdIn.readLine

//Handy functions for reading user input while printing information in a fancy way
//Some of these functions also clear the terminal, such as spawnAndRead()
//The "loop" functions are good for when you only want to proceed once the user specifies a correct answer

def readUserInput(message: String = ""): String =
  if message != "" then
    println(message)
  readLine()

def spawnAndRead(message: String): String = readUserInput(s"\u001B[3J\u001B[1J\u001B[H$message")

def askPrompt(ui: String, clear: Boolean = true): Boolean =
  val yellow = foreground("yellow")
  val default = foreground("default")
  val answer =
    if clear then
      spawnAndRead(s"\u001B[3J\u001B[1J\u001B[H$ui ${yellow}(y/n)$default\n").toLowerCase
    else
      readUserInput(s"$ui ${yellow}(y/n)$default\n").toLowerCase
  answer == "yes" || answer == "y"

def answerToNumber(str: String): Int =
  try
    str.toInt
  catch
    case e: Exception => -1

def answerToShort(str: String): Short =
  try
    str.toShort
  catch
    case e: Exception => -1

def answerToByte(str: String): Byte =
  try
    str.toByte
  catch
    case e: Exception => -1

def pressToContinue(message: String = ""): String = readUserInput(message + "\n\nPress enter to continue")

private def formList(l: Seq[String], title: String = "Choose an entry", first: String = "Exit", txt: String = "", i: Int = 0): String =
  if i >= l.length then
    s"$title\n\n${foreground("green")}0: ${foreground("default")}$first\n\n$txt"
  else
    val line = s"${foreground("green")}${i+1}:${foreground("default")} ${l(i)}\n"
    formList(l, title, first, txt + line, i+1)

private def formList_array(l: Array[String], title: String = "Choose an entry", first: String = "Exit", txt: String = "", i: Int = 0): String =
  if i >= l.length then
    s"$title\n\n${foreground("green")}0: ${foreground("default")}$first\n\n$txt"
  else
    val line = s"${foreground("green")}${i+1}:${foreground("default")} ${l(i)}\n"
    formList_array(l, title, first, txt + line, i+1)

private def formList_long(
l: Seq[String], size: Int,
title: String = "Choose an entry", first: String = "Exit",
current: Int = 0,
txt: String = "", i: Int = 0
): String =
  if i >= l.length then
    s"$title\n\n${foreground("green")}0: ${foreground("default")}$first\n\n$txt"
  else
    if current >= size-1 then
      val line = s"${foreground("green")}${i+1}:${foreground("default")} ${l(i)}\n"
      formList_long(l, size, title, first, 0, txt + line, i+1)
    else
      val line = s"${foreground("green")}${i+1}:${foreground("default")} ${l(i)}\t\t"
      formList_long(l, size, title, first, current + 1, txt + line, i+1)

private def findStringMatch(opts: Seq[String], key: String, i: Int = 0): Int =
  if i >= opts.length then -1
  else if opts(i) == key then i
  else findStringMatch(opts, key, i+1)

def readLoop(txt: String, maxval: Int): Int =
  val answer = answerToNumber(spawnAndRead(txt))
  if answer == 0 || (answer > 0 && answer <= maxval) then
    answer
  else
    readLoop(txt, maxval)

def chooseOption(l: Seq[String], title: String = "Choose an entry", first: String = "Exit"): Int =
  val txt_list = formList(l, title, first)
  readLoop(txt_list, l.length)

def chooseOption_array(l: Array[String], title: String = "Choose an entry", first: String = "Exit"): Int =
  val txt_list = formList_array(l, title, first)
  readLoop(txt_list, l.length)

def chooseOption_string(l: Seq[String], title: String = "Choose an entry", first: String = "Exit"): String =
  val i = chooseOption(l, title, first)
  if i == 0 then ""
  else l(i-1)

def chooseOption_astring(l: Array[String], title: String = "Choose an entry", first: String = "Exit"): String =
  val i = chooseOption_array(l, title, first)
  if i == 0 then ""
  else l(i-1)

def chooseOption_h(l: Seq[String], size: Int = 3, title: String = "Choose an entry", first: String = "Exit"): Int =
  val txt_list = formList_long(l, size, title, first)
  readLoop(txt_list, l.length)

def chooseOption_hs(l: Seq[String], size: Int = 3, title: String = "Choose an entry", first: String = "Exit"): String =
  val i = chooseOption_h(l, size, title, first)
  if i == 0 then ""
  else l(i-1)

def readInt(txt: String): Int =
  val answer = answerToNumber(spawnAndRead(txt))
  if answer != -1 then
    answer
  else
    readInt(txt)

def readShort(txt: String): Short =
  val answer = answerToShort(spawnAndRead(txt))
  if answer != -1 then
    answer
  else
    readShort(txt)

def readByte(txt: String): Byte =
  val answer = answerToByte(spawnAndRead(txt))
  if answer != -1 then
    answer
  else
    readByte(txt)

def chooseOption_dir(txt: String): String =
  val answer = spawnAndRead(txt)
  if answer == "" then
    "."
  else if File(answer).isDirectory() then
    answer
  else
    pressToContinue("That is not a real directory in your system!")
    chooseOption_dir(txt)

def chooseOption_file(txt: String): String =
  val answer = spawnAndRead(txt)
  if File(answer).isFile() then
    answer
  else
    pressToContinue("That is not a real file in your system!")
    chooseOption_file(txt)
