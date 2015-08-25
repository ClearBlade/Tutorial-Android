# Android Tutorial for ClearBlade

This tutorial uses the ClearBlade Android API to communicate with the platform.

## Setup

#### Installing Android Studio
- Download and install Android Studio from https://developer.android.com/sdk/index.html and configure the SDKs from the instructions
given from https://developer.android.com/sdk/installing/index.html

#### Clone this repository and open in Android Studio
- Do a ``` git clone https://github.com/ClearBlade/Tutorial-Android.git```
- Start up Android Studio and on the welcome screen select, 'Open an existing Android Studio Project' (OR go to File -> Open) and 
browse to the Tutorial-Android directory and hit open

#### Editing PlatformConstants.java to add the SystemKey, SystemSecret, PlatformURL and MessagingURL
- In Android Studio navigate to Tutorial-Android -> app -> src -> main -> java -> tutorial.clearblade.com.clearbladetutorial
- Open the PlatformConstants.java file and add your systemkey, systemsecret, platformURL and messagingURL
```java
public final static String SYSTEMKEY = "YOUR_SYSTEMKEY";
public final static String SYSTEMSECRET = "YOUR_SYSTEMSECRET";
public final static String PLATFORM_URL = "YOUR_PLATFORMURL";
public final static String MESSAGING_URL = "YOUR_MESSAGINGURL";
```

Then hit the 'Run' button and enjoy the turorial!
