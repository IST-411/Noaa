/* 
Project: L03 Group Work
Purpose Details: Connections NOAA Web Services
Course: IST 411
Author: Ahmed Metwoali, Alexa McInvaille, Elyse Swider, Ryan Waters
Date Developed: 6/03/21
Last Date Changed: 6/03/21
Revision: 1
 */
package noaaproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;

public class Noaa {
    public static void main(String args[]){
        String accessToken = "jqitmMthppedosSPTdNkjxLrmiDoNPNm";
        Gson gson = new Gson();
        String response;
        HttpURLConnection connection = null;
        try
        {
            URL url = new URL("https://www.ncdc.noaa.gov/cdo-web/api/v2/datasets");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("token",accessToken);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            response = br.readLine();
//            System.out.println(response + "\n");
            NoaaData noaa = gson.fromJson(response, NoaaData.class);
            
            //Result Set -- needs data
            System.out.println("Result Set");
            System.out.println("----------");
            System.out.printf("%-15s%s\n", "Offset", noaa.getMetadata().getResultSet().getOffset());
            System.out.printf("%-15s%s\n", "Count", noaa.getMetadata().getResultSet().getCount());
            System.out.printf("%-15s%s\n", "Limit", noaa.getMetadata().getResultSet().getCount());
            System.out.println();
            
            //Results
            int count = 1;
            for (Results res : noaa.getResults())
            {
                System.out.println("Result " + count++);
                System.out.println("----------");
                System.out.printf("%-15s%s\n", "Uid", res.getUid());
                System.out.printf("%-15s%s\n", "Min Date", res.getMindate());
                System.out.printf("%-15s%s\n", "Max Date", res.getMaxdate());
                System.out.printf("%-15s%s\n", "Name", res.getName());
                System.out.printf("%-15s%s\n", "Data Coverage", res.getDatacoverage());
                System.out.printf("%-15s%s\n", "ID", res.getId());
                System.out.println();
            }

        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            connection.disconnect();
        }
    }
}
