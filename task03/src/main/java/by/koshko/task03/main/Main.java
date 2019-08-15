package by.koshko.task03.main;

import by.koshko.task03.entity.Composite;
import by.koshko.task03.service.*;

import java.util.StringJoiner;


public class Main {

    public static void main(final String[] args) {
        StringJoiner joiner = new StringJoiner("\n    ");
        joiner.add("    The organization of a simple bus-oriented computer is shown in Fig. 2-1. TheCPU(Central Processing Unit) is the ‘‘brain’’ of the computer. Its function is to execute programs stored in the main memory by fetching their instructions, examining them, and then executing them one after another. The components are connected by a bus, which is a collection of parallel wires for transmitting address,data, and control signals. Buses can be external to the CPU, connecting it to mem-ory and I/O devices, but also internal to the CPU, as we will see shortly. Modern computers have multiple buses.");
        joiner.add("The CPU is composed of several distinct parts. The control unit is responsible for fetching instructions from main memory and determining their type. The arithmetic logic unit performs operations such as addition and Boolean AND needed to carry out the instructions.");
        joiner.add("The CPU also contains a small, high-speed memory used to store temporary results and certain control information. This memory is made up of a number of registers, each having has a certain size and function. Usually, all the registers have the same size. Each register can hold one number, up to some maximum determined by its size. Registers can be read and written at high speed since they are internal to the CPU.");
        joiner.add("The most important register is theProgram Counter(PC), which points to the next instruction to be fetched for execution. (The name ‘‘program counter’’ is somewhat misleading because it has nothing to do with counting anything, but the term is universally used.) Also important is theInstruction Register(IR), which holds the instruction currently being executed. Most computers have numerous other registers as well, some of them general purpose as well as some for specific purposes. Yet other registers are used by the operating system to control the computer.");
//        joiner.add("The most important register is theProgram Counter(PC), which points to the next instruction to be fetched for execution. ( The name ‘‘program counter’’ is somewhat misleading because it has nothing to do with counting anything, but the term is universally used.) Also important is theInstruction Register(IR), which holds the instruction currently being executed. Most computers have numerous other registers as well, some of them general purpose as well as some for specific purposes. Yet other registers are used by the operating system to control the computer.");
        TextParser parser = new TextParser();
        ParagraphParser parser1 = new ParagraphParser();
        SentenceParser parser2 = new SentenceParser();
        CharParser charParser = new CharParser();
        LexemeParser parser3 = new LexemeParser(charParser);
        parser.setNext(parser1);
        parser1.setNext(parser2);
        parser2.setNext(parser3);
        parser3.setNext(charParser);
        Composite text = new Composite(Composite.Type.TEXT);
        parser.parse(joiner.toString(), text);
        System.out.println(text.compose());
    }

}
