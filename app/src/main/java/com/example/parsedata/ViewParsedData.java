package com.example.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ViewParsedData extends AppCompatActivity {
    int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_parsed_data);

         mode = getIntent().getIntExtra("mode", 0);
         System.out.println("----------printing mode value as "+ mode+" ----------------");

         if(mode==1){
             parseJson();
         }
         if(mode==2){
             System.out.println("-----------------hello entered to xml ---------------------");
             parseXML();
         }
    }

    public void parseJson(){
        System.out.println("-------------- didn't enter try block");
        try {
            // creating inputstream of perticular file
            InputStream inputStream = getAssets().open("input.json");

            // getting the size of inputstream for construction of array
            // i.e is to store the stream of binary data.
            int size = inputStream.available();

            // creating a byte array to store the input-stream of file
            byte buffer[] = new byte[size];

            // store the inputstream in buffer variable
            inputStream.read(buffer);

            // convert the byte array to string
            String byteToString = new String(buffer);
            Log.d("data", "parseJson: "+ byteToString);
            System.out.println(byteToString);

            //counter string representing the json object to jsonObject
            JSONObject jsonObject = new JSONObject(byteToString);

            JSONObject cityObject = jsonObject.getJSONObject("city");

            String name = cityObject.getString("city-name");
            System.out.println("-----------------------------------");
            System.out.println(name);
            String longitude = cityObject.getString("longitude");
            String latitude = cityObject.getString("latitude");
            String temperature = cityObject.getString("temperature");
            String humidity = cityObject.getString("humidity");
            Log.e("json", "parseJson: print name"+name );

            TextView jsonPlaceHolder;
            jsonPlaceHolder =(TextView) findViewById(R.id.jsonPlaceHolder);

            jsonPlaceHolder.setText(name +"\n");
            jsonPlaceHolder.append(longitude+"\n");
            jsonPlaceHolder.append(latitude+"\n");
            jsonPlaceHolder.append(temperature+"\n");
            jsonPlaceHolder.append(humidity+"\n");
            
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    void parseXML(){
        System.out.println("------------------entered to parseXML fuction------------------");
        try {
            InputStream inputStream = getAssets().open("input.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document xmlParsedFile  =  documentBuilder.parse(inputStream);

            NodeList nodeList = xmlParsedFile.getElementsByTagName("city");

            System.out.println("------------------------------------------------");
            for(int i=0; i<nodeList.getLength();i++){
                Node city = nodeList.item(i);
                if (city.getNodeType() == Node.ELEMENT_NODE){

//                    Element c = (Element) city;
//                    String id = c.getAttribute("id");
                    NodeList cityDetailList = city.getChildNodes();

                    for (int j=0; j<cityDetailList.getLength();j++){
                        
                    }
                }

                System.out.println(nodeList.item(i).toString());
            }

        }catch (IOException e){
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}