package com.talentica;

import java.util.Date;

public class JavaTextBlocks {

    public static void main(String[] args) {
        sampleTextBlock();
    }

    public static void sampleTextBlock(){
        String textBlock = """
                   hello world
                   """;
        System.out.println(textBlock);
    }

    public static void jsonTextBlock() {
        String jsonString = "{\n"
                + " \"isbn\": \"123-456-222\",\n"
                + " \"author\":\n"
                + "    {\n"
                + "      \"lastname\": \"Doe\",\n"
                + "      \"firstname\": \"Jane\"\n"
                + "    },\n"
                + " \"editor\":\n"
                + "    {\n"
                + "      \"lastname\": \"Smith\",\n"
                + "      \"firstname\": \"Jane\"\n"
                + "    },\n"
                + "  \"title\": \"The Ultimate Database Study Guide\",\n"
                + "  \"category\": [\"Non-Fiction\", \"Technology\"]\n"
                + "}";

        String jsonTextBlock = """
                {
                 "isbn": "123-456-222",
                 "author":
                    {
                      "lastname": "Doe",
                      "firstname": "Jane"
                    },
                 "editor":
                    {
                      "lastname": "Smith",
                      "firstname": "Jane"
                    },
                  "title": "The Ultimate Database Study Guide",
                  "category": ["Non-Fiction", "Technology"]
                }
                """;

        System.out.println("Json as string : ");
        System.out.println(jsonString);

        System.out.println("Json as text block : ");
        System.out.println(jsonTextBlock);
    }


    public static void textBlockWithTrailingSpaces() {
        String textBlockWithWhiteSpace = """
            This is a trailing white space       \s
            """;
    }


    public static void textBlocksWithFormatting() {
        Date currentDate = new Date();

        String formatTextBlock = """
                    Hello %s,
                        this is content added on year %tY.
                    Regards,
                    %s
                    """.formatted("Reader", currentDate ,  "Author");

        System.out.println(formatTextBlock);

        /**
         * Output :
         *
         * Hello Reader,
         *     this is content added on 2023.
         * Regards,
         * Author
         *
         */
    }


    public static void textBlockWithLineContinuation() {

        String longString = " This is a long line that exceeds the desired line length."
                + "You can split it using the line continuation character to maintain the desired line length.";

        String longStringTextBlock = """
                        This is a long line that exceeds the desired line length.\
                        You can split it using the line continuation character to maintain the desired line length.
                        """;

        System.out.println(longStringTextBlock);
    }

}