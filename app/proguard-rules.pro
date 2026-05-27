-keep class com.keyboardxboxcontroller.** { *; }
-keep enum com.keyboardxboxcontroller.ControllerButton { *; }
-keep enum com.keyboardxboxcontroller.ControllerTrigger { *; }
-keepclassmembers class com.keyboardxboxcontroller.** {
    public <init>(...);
    public *** get*();
    public void set*(...);
}
-dontwarn com.keyboardxboxcontroller.**
