package com.company;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        try {
            Scanner s1 = new Scanner(new FileReader("seed_G1.edgelist"));
            Scanner s11 = new Scanner(new FileReader("seed_G1.edgelist"));
            ArrayList<Integer> G1column1 = new ArrayList<Integer>();

            while (s1.hasNextInt()) {
                G1column1.add(s1.nextInt());
                s1.nextInt();
            }
            NodeTypeConstructor[] G1Array = new NodeTypeConstructor[G1column1.size()];
            for (int i = 0; i < G1Array.length; i++){
                G1Array[i] = new NodeTypeConstructor(s11.nextInt(), s11.nextInt());
            }

            s1.close();

            Scanner s2 = new Scanner(new FileReader("seed_G1.edgelist"));
            ArrayList<Integer> G1column2 = new ArrayList<Integer>();

            while (s2.hasNextInt()) {
                s2.nextInt();
                G1column2.add(s2.nextInt());
            }
            s2.close();

            Scanner s3 = new Scanner(new FileReader("seed_G2.edgelist"));
            Scanner s33 = new Scanner(new FileReader("seed_G2.edgelist"));
            ArrayList<Integer> G2column1 = new ArrayList<Integer>();

            while (s3.hasNextInt()) {
                G2column1.add(s3.nextInt());
                s3.nextInt();
            }

            NodeTypeConstructor[] G2Array = new NodeTypeConstructor[G2column1.size()];
            for (int i = 0; i < G2Array.length; i++){
                G2Array[i] = new NodeTypeConstructor(s33.nextInt(), s33.nextInt());
            }


            Scanner s4 = new Scanner(new FileReader("seed_G2.edgelist"));
            ArrayList<Integer> G2column2 = new ArrayList<Integer>();

            while (s4.hasNextInt()) {
                s4.nextInt();
                G2column2.add(s4.nextInt());
            }


            s2.close();

            ArrayList<Integer> G1column1Count = new ArrayList<Integer>();
            double score = 0;
            int count = 0;
            for (int i = 0; i < 4091; i++){
                count = Collections.frequency(G1column1,i);
                G1column1Count.add(count);
            }
//            System.out.println("G1column1Count: " + G1column1Count);

            ArrayList<Integer> G2column1Count = new ArrayList<Integer>();
            int count2 = 0;
            for (int i = 0; i < 4091; i++){
                count2 = Collections.frequency(G2column1,i);
                G2column1Count.add(count2);
            }
//            System.out.println("G2column1Count: " + G2column1Count);




            Scanner s5 = new Scanner(new FileReader("seed_node_pairs.txt"));


            NodeTypeConstructor[] mappedArray = new NodeTypeConstructor[500];
            for (int i = 0; i < 500; i++){
                mappedArray[i] = new NodeTypeConstructor(s5.nextInt(),s5.nextInt());
            }

            ArrayList<Integer> forLoopCounter = new ArrayList<Integer>();
            ArrayList<Double> graphScores = new ArrayList<Double>();
            int current=-99;
            for (int i = 0; i < G1Array.length; i++){
                int scoreCounter = 0;
                current = G1Array[i].getColumn1();
                if (G1Array[i].getColumn1()!=4090){
                    while (G1Array[i].getColumn1() == current) {
                        i++;
                    }
                    }

                for (int j = 0; j < mappedArray.length; j++){

                    if (G1Array[i].getColumn1() == mappedArray[j].getColumn1()){
                        scoreCounter++;
                    }
                    if (G1Array[i].getColumn1() == mappedArray[j].getColumn2()){
                        scoreCounter++;
                    }
                    if (G1Array[i].getColumn2() == mappedArray[j].getColumn1()){
                        scoreCounter++;
                    }
                    if (G1Array[i].getColumn2() == mappedArray[j].getColumn2()){
                        scoreCounter++;
                    }

//                    if (G2Array[i].getColumn1() == mappedArray[j].getColumn1()){
//                        scoreCounter++;
//                    }
//                    if (G2Array[i].getColumn1() == mappedArray[j].getColumn2()){
//                        scoreCounter++;
//                    }
                    if (G2Array[i].getColumn2() == mappedArray[j].getColumn1()){
                        scoreCounter++;
                    }
                    if (G2Array[i].getColumn2() == mappedArray[j].getColumn2()){
                        scoreCounter++;
                    }
                    forLoopCounter.add(scoreCounter);

                }
            }

            for(int i = 0; i < G1column1Count.size();i++){
                score = (forLoopCounter.get(i)) / (Math.sqrt(G1column1Count.get(i)) * Math.sqrt(G2column1Count.get(i)));
                graphScores.add(score);
            }
            System.out.println(graphScores);

            double n=graphScores.size(),sum=0,mean;
            for(int i=0;i<n;i++)
            {if (graphScores.get(i) >0 && graphScores.get(i) < 10)
                sum=sum+graphScores.get(i);
            }
            mean=sum/n;
            sum=0;
            for(int i=0;i<n;i++)
            {if (graphScores.get(i) >0 && graphScores.get(i) < 10)
                sum+=Math.pow((graphScores.get(i)-mean),2);

            }
            mean=sum/(n-1);
            double deviation=Math.sqrt(mean);


//            System.out.println("GraphScores: " + graphScores);

            ArrayList<Double> graphScoresCopy = new ArrayList<Double>();
            graphScoresCopy = (ArrayList)graphScores.clone();
            Collections.sort(graphScoresCopy);
            Collections.reverse(graphScoresCopy);
//            System.out.println(graphScoresCopy);



            double ECCE = .045;

            ArrayList<Integer> OutputLL1 = new ArrayList<Integer>();
            ArrayList<Integer> OutputLL11 = new ArrayList<Integer>();
            NodeTypeConstructor[] outputArray = new NodeTypeConstructor[4092];
            for(int i = 0; i < graphScores.size();i++){
                if (graphScores.get(i) >= ECCE){
                    outputArray[i] = new NodeTypeConstructor(G1Array[i].getColumn1(),G2Array[i].getColumn1());
                    OutputLL1.add(G1Array[i].getColumn1());
                    OutputLL11.add(G2Array[i].getColumn1());

                }
            }


            try {
                File myObj = new File("output.txt");
                FileWriter myWriter = new FileWriter("output.txt");
                for (int i = 0;i<OutputLL1.size();i++){
                    String str = OutputLL1.get(i).toString();
                    myWriter.write(str+" ");
                    str = OutputLL11.get(i).toString();
                    myWriter.write(str+"\n");


                }

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
