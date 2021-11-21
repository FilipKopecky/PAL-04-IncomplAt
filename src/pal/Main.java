package pal;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[30001]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            return ret;
        }


        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        int numStates = reader.nextInt();
        int alphabetSize = reader.nextInt();
        int numFinalStates = reader.nextInt();
        int numPositive = reader.nextInt();
        int numNegative = reader.nextInt();
        int exampleLength = reader.nextInt();

        int[][] states = new int[numStates][alphabetSize];
        for (int i = 0; i < numStates; i++) {
            reader.nextInt();
            for (int j = 0; j < alphabetSize; j++) {
                states[i][j] = reader.nextInt();
            }
        }
        ArrayList<char[]>words = new ArrayList<>();
      //  byte[][] words = new byte[numPositive][exampleLength];
        for (int i = 0; i < numPositive; i++) {
               //System.out.print(reader.readLine());
                char[]word=reader.readLine().substring(0,exampleLength).toCharArray();
                words.add(word);
        }
        for (int i = 0; i < numNegative; i++) {
           reader.readLine();
        }
      //  System.out.println("DONE");
        //int[] vector = new int[numFinalStates + 1];
       Alg alg = new Alg(states,words,numPositive,exampleLength);

        for (int i = 0; i < numStates; i++) {
            ArrayList<Integer>vector= new ArrayList<>();
           
            for (int j = 0; j < numPositive; j++) {
                //System.out.print(alg.walkThrough(i,j)+" ");
                int discovered = alg.walkThrough(i,j);
                if(!vector.contains(discovered))
                    vector.add(discovered);
            }
            if(vector.size()==numFinalStates)
            {
                Collections.sort(vector);
                StringBuilder builder = new StringBuilder();
                builder.append(i);
                builder.append(" ");
                for (int j = 0; j < numFinalStates-1; j++) {
                    builder.append((vector.get(j)));
                    builder.append(" ");
                }
                builder.append((vector.get(numFinalStates-1)));
                System.out.println(builder);
            }

        }


        // write your code here
    }

}
