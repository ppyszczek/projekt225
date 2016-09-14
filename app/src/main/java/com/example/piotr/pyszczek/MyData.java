package com.example.piotr.pyszczek;

public class MyData {

    public static int resourceId;
    public static String description;
    public static String[] answers;
    public static int INT_NUMBER_OF_QUESTIONS = 5;
    public static int INT_TEST_NUMBER=1;

    public static void prepareQuestion(int inCounter){

        answers = new String[3];
        if (INT_TEST_NUMBER == 1) {
            switch (inCounter){

                case 0:
                    resourceId = R.drawable.pytanie1;
                    description = "Rok otwarcia Hali Stulecia?";
                    answers[0] = "1913";
                    answers[1] = "1911";
                    answers[2] = "1915";
                    break;
                case 1:
                    resourceId = R.drawable.pytanie2;
                    description = "W którym wieku otwarto Kopalnię soli Bochnia?";
                    answers[0] = "13";
                    answers[1] = "12";
                    answers[2] = "10";
                    break;
                case 2:
                    resourceId = R.drawable.pytanie3;
                    description = "Na jakiej ulicy znajduje się zamek w Malborku?";
                    answers[0] = "Starościńska 1";
                    answers[1] = "Norwida 7";
                    answers[2] = "Kołątaja 15";
                    break;
                case 3:
                    resourceId = R.drawable.pytanie4;
                    description = "Jaką powierzchnię ma Zamek Królewski na Wawelu (m^2)?";
                    answers[0] = "7040";
                    answers[1] = "5017";
                    answers[2] = "8250";
                    break;
                case 4:
                    resourceId = R.drawable.pytanie5;
                    description = "Położenie Białowiskiego Parku Narodowego";
                    answers[0] = "woj. podlaskie";
                    answers[1] = "woj. mazowieckie";
                    answers[2] = "woj. lubelskie";
                    break;
                default:
                    break;
            }
        }
        else if (INT_TEST_NUMBER == 2) {
            switch (inCounter){

                case 0:
                    resourceId = R.drawable.pytanie6;
                    description = "Jaki jest rok wpisania Hali Stulecia na listę?";
                    answers[0] = "2006";
                    answers[1] = "2000";
                    answers[2] = "2013";
                    break;
                case 1:
                    resourceId = R.drawable.pytanie7;
                    description = "W jakim dniu Kopalnia soli Bochnia została wpisana na listę?";
                    answers[0] = "23 czerwca";
                    answers[1] = "17 września";
                    answers[2] = "3 kwietnia";
                    break;
                case 2:
                    resourceId = R.drawable.pytanie8;
                    description = "W jakich latach była to rezydencja królów Polski?";
                    answers[0] = "1457–1772";
                    answers[1] = "1564-1723";
                    answers[2] = "1265-1817";
                    break;
                case 3:
                    resourceId = R.drawable.pytanie9;
                    description = "Ile sal wystawowych znajduje się w Zamku Królewskim na Wawelu";
                    answers[0] = "71";
                    answers[1] = "86";
                    answers[2] = "65";
                    break;
                case 4:
                    resourceId = R.drawable.pytanie10;
                    description = "Data utworzenia Białowiskiego Parku Narodowego";
                    answers[0] = "11 sierpnia 1932";
                    answers[1] = "19 marca 1938";
                    answers[2] = "17 lipca 1917";
                    break;
                default:
                    break;
            }
        }

    }

}
