package br.edu.trabalho.social.infra;
public class Logger { public static void info(String m){ System.out.println("[INFO] "+m);} public static void warn(String m){ System.out.println("[WARN] "+m);} public static void error(String m, Throwable t){ System.err.println("[ERROR] "+m+" -> "+t.getMessage()); } }
