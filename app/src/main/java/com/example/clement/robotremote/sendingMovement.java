package com.example.clement.robotremote;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by clement on 03/01/2017.
 */
public class sendingMovement extends Thread {

    private boolean encours = true;
    private byte [] ipRobot = {(byte)192,(byte)168,(byte)42,(byte)1};
    private int port=2155;
    private Socket socket;
    public boolean sendDone = true;
    public String fuck = "192.168.42.1";
    public String SendString;

    public sendingMovement(){

    }

    public void run(){

        tcpConnect();

        while(encours){

            if(robotState.changed == true){

                robotState.changed = false;

                while(robotState.ihmButtonDown == true){

                        /*PrintWriter outTest = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                        outTest.println("Test");*/
                        tcpSend();

                    //sendMovement();  // Send data through socket
                    //System.out.println("Message: "+robotState.getCommand());
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                tcpSend();
                //sendMovement();//Then send the last message even if the button is up
                //System.out.println("Message: "+robotState.getCommand());
            }

            try {
                Thread.sleep(400); // Wait before sending data again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void tcpConnect(){



        InetAddress serverAddr;
        try {
            serverAddr = InetAddress.getByName(fuck);
            System.out.println("IS CONNECTING");
            Log.d("TCP", "C: Connecting...");

            try {
                socket = new Socket(serverAddr, port);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void tcpSend(){
        SendString = robotState.getCommand(); //Get command to send
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            out.print(SendString);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void sendMovement(){

        DatagramSocket socket;

        String msgToSend = robotState.getCommand(); //Get movement to send
        byte[] message = new byte [msgToSend.length()];
        message = msgToSend.getBytes();


        try{
            InetAddress adress = InetAddress.getByAddress(ipRobot);
            socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(message,msgToSend.length(),adress,port); // Create datagram
            socket.send(packet);
            socket.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }*/

}
