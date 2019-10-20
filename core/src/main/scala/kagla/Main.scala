package kagla

import kagla.`object`.Environment.newEnvironment
import kagla.evaluator.Evaluator.eval
import kagla.lexer.Lexer
import kagla.parser.Parser
import kagla.repl.Repl

import scala.io.Source

object Main extends App {
  if (args.isEmpty) {
    val user = System.getProperty("user.name")
    println(s"Hello $user! This is the Monkey programming language!")
    println("Feel free to type in commands")
    Repl.start(System.in, System.out)
  } else {
    val file = Source.fromFile(args.head)
    val source = try file.mkString
    finally file.close()

    val l = Lexer(source)
    val p = Parser(l)

    val program = p.parseProgram()
    if (p.errors.nonEmpty) {
      println(Repl.MONKEY_FACE)
      println("Woops! We ran into some monkey business here!\n")
      println(" parser errors:\n")
      for (msg <- p.errors) {
        println(s"\t$msg\n")
      }
    } else {
      val env       = newEnvironment
      val evaluated = eval(program, env)
      if (evaluated != null) {
        println(evaluated.inspect)
      }
    }
  }
}
