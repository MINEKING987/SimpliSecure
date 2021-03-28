#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiManager.h>


boolean PIRstate ; //variable to store PIR state
boolean lastPIRstate = HIGH;
int PIR = 0; //PIR connected to GPIO 0

void setup () {
  Serial.begin(115200);
   
//  WiFiManagerParameter IP("Enter IP", "Enter IP", "192.168.1.15:8888", 40);
//  wifiManager.addParameter(&IP);
//  
  WiFiManager wifiManager;
  wifiManager.autoConnect("AutoConnectAP");
  
  pinMode(LED_BUILTIN, OUTPUT);
  Serial.print("LED high");
  digitalWrite(LED_BUILTIN, HIGH);
  
//  strcpy(ip,IP.getValue());
//  json["ip"]=ip;
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(1000);
    Serial.println("waiting");
  }

  pinMode(PIR, INPUT); //digitalWrite(PIR, LOW);
  digitalWrite(LED_BUILTIN, LOW);
  

  delay(30000);
  Serial.println("delay done");
}

void loop()
{

  PIRstate = digitalRead(PIR);  //HIGH when motion detected, else LOW
  if (PIRstate == HIGH)  //Checking if there is any motion
  {
    
    digitalWrite(LED_BUILTIN, HIGH);
    Serial.println("is High");
    delay(100);
    digitalWrite(LED_BUILTIN, LOW);
    if (WiFi.status() == WL_CONNECTED)  //Check WiFi connection status
    {
      HTTPClient http;  //Declare an object of class HTTPClient

      http.begin("http://192.168.1.15:8888");  //Specify request destination
      http.GET();   //Send the request
      http.end();   //Close connection

    }
    delay(2.5*1000);
  }
}
