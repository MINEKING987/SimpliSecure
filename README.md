# SimpliSecure

This project allows users to turn their old android devices into security devices.This works with most devices running android 5+.

This can be used in a standalone mode which unfortunately wont work in the dark. But the other version which includes connecting an ESP will work in the dark too!

## Try the app [here](app/release/app-release.apk)

## Check out a Demonstration of the project [Here](https://youtu.be/g4UWgCsEWNU) 

## using the app
### 1. standalone Mode
1. open the app
2. give requested permissions
3. select Standalone mode
4. The service will now have started!
5. When movement is detected infront of the camera, A photo is captured and sent to Face recognition program to check for faces,if present,image will be saved.
6. it is Recommonded to use Screen-pinning to increase security
##### Note: this method is only reliable if there is enough ambient light.

### 2.ESP-01 mode
1. open the app
2. give permissions
3. select ESP-01 mode.
4. Setup the ESP-01 for working with the android.
5. The mode should now be working!
6. it is Recommonded to use Screen-pinning to increase security

## setting up the ESP
1. connect the esp-01 to a programmer and connect gpio-0 to ground to start programming mode of ESP
2. flash the given [code](ESP_simpli_secure.ino) to the ESP using the Arduino IDE.
3. connect the output of a PIR sensor to gpio-0.
4. power the ESP and the PIR sensor and connect to it's wifi. The portal page the opens subsequently allows you to give the ESP wifi-credentials to login from the next time.
##### Note: The ESP requires 3.3v with a minimum of 80 mA for proper functioning.

## Finding captured Images
The images captured by the app will be saved in a folder called "SimpliSecure". Just check your Pictures folder for the folder name.
