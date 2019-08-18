package by.koshko.task03.main;


import by.koshko.task03.entity.TextComposite;
import by.koshko.task03.service.LexemeParser;
import by.koshko.task03.service.ParagraphParser;
import by.koshko.task03.service.SentenceParser;
import by.koshko.task03.service.SortingService;
import by.koshko.task03.service.SymbolParser;
import by.koshko.task03.service.TextParser;

import java.util.StringJoiner;

public class Main {
    public static void main(final String[] args) {
        StringJoiner joiner = new StringJoiner("\n\t");
        joiner.add("The organization of a simple bus-oriented computer is shown in Fig. 2-1. The\nCPU(Central Processing Unit) is the ‘‘brain’’ of the computer. Its function is to\nexecute programs stored in the main memory by fetching their instructions, exam-\nining them, and then executing them one after another.\n The components are con-\nnected by a bus, which is a collection of parallel wires for transmitting address,\ndata, and control signals. Buses can be external to the CPU, connecting it to mem-\nory and I/O devices, but also internal to the CPU, as we will see shortly. Modern\n computers have multiple buses.");
        joiner.add("The CPU is composed of several distinct parts. The control unit is responsible\n for fetching instructions from main memory and determining their type. The arithmetic\n logic unit performs operations such as addition and Boolean AND needed to\n carry out the instructions.");
        joiner.add("The CPU also contains a small, high-speed memory used to store temporary results\n and certain control information. This memory is made up of a number of registers,\n each having has a certain size and function. Usually, all the registers have\n the same size. Each register can hold one number, up to some maximum determined\n by its size. Registers can be read and written at high\n speed since they are internal to the CPU.");
        joiner.add("The most important register is theProgram Counter(PC), which points to the next\n instruction to be fetched for execution. (The name ‘‘program counter’’ is somewhat \nmisleading because it has nothing to do with counting anything, but the term is \nuniversally used.) Also important is theInstruction Register(IR), which holds\n the instruction currently being executed. Most computers have numerous other registers\n as well, some of them general purpose as well as some for specific purposes. Yet\n other registers are used by the operating system to control the computer.");
        //===============================//
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        SymbolParser symbolParser = new SymbolParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
        //===============================//
        TextComposite textComposite = new TextComposite();
        textParser.parse(joiner.toString(), textComposite);
//        System.out.println(textComposite.compose());
        //===============================//
        SortingService sortingService = new SortingService(textComposite);
        sortingService.sort3('a');
        System.out.println(textComposite.compose());
    }
}
