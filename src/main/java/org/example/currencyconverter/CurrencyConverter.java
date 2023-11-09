package org.example.currencyconverter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) throws IOException {
        HashMap<Integer, String> currencyCodes = new HashMap<>();
        currencyCodes.put(1, "USD"); // United States Dollar
        currencyCodes.put(2, "EUR"); // Euro
        currencyCodes.put(3, "JPY"); // Japanese Yen
        currencyCodes.put(4, "GBP"); // British Pound Sterling
        currencyCodes.put(5, "AUD"); // Australian Dollar
        currencyCodes.put(6, "CAD"); // Canadian Dollar
        currencyCodes.put(7, "CHF"); // Swiss Franc
        currencyCodes.put(8, "CNY"); // Chinese Yuan
        currencyCodes.put(9, "SEK"); // Swedish
        currencyCodes.put(10, "NZD"); // New Zealand Dollar

        String fromCode, toCode;
        double amount;

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the CodSoft Currency Converter");
        System.out.println("Currency converting FROM?");
        System.out.println("1:USD (United States Dollar) \t 2:EUR (Euro) \t 3:JPY (Japanese Yen) \t 4:GBP (British Pound Sterling) \t 5:AUD (Australian Dollar)" +
                " \t 6:CAD (Canadian Dollar) \t 7:CHF (Swiss Franc) \t 8:CNY (Chinese Yuan) \t 9:SEK (Swedish) \t 10:NZD (New Zealand Dollar) \t");
        fromCode = currencyCodes.get(sc.nextInt());

        System.out.println("Currency converting TO?");
        System.out.println("1:USD (United States Dollar) \t 2:EUR (Euro) \t 3:JPY (Japanese Yen) \t 4:GBP (British Pound Sterling) \t 5:AUD (Australian Dollar)" +
                " \t 6:CAD (Canadian Dollar) \t 7:CHF (Swiss Franc) \t 8:CNY (Chinese Yuan) \t 9:SEK (Swedish) \t 10:NZD (New Zealand Dollar) \t");
        toCode = currencyCodes.get(sc.nextInt());

        System.out.println("Amount?");
        amount = sc.nextFloat();

        sendHttpGetRequest(fromCode, toCode, amount);

        System.out.println("Thank you for using CodSoft Currency Converter :) ");

    }

    private static void sendHttpGetRequest(String fromCode, String toCode, double amount) throws IOException {
        String accessKey = "2d0db8773fb2a996572effcc";
        DecimalFormat formatter = new DecimalFormat("00.00");
        String getURL = "https://v6.exchangerate-api.com/v6/" + accessKey + "/pair/" + fromCode + "/" + toCode + "/" + amount;
        URL url = new URL(getURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            } in.close();
            String jsonString = response.toString();
            JSONObject obj = new JSONObject(response.toString());
            Double exchangeRate = obj.getDouble("conversion_result");
            System.out.println(exchangeRate);
            System.out.println(amount + " " + fromCode + " = " + formatter.format(exchangeRate) + " " + toCode);
        } else {
            System.out.println("GET request failed!");
        }
    }
}
