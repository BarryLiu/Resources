// IMusicAidlInterface.aidl
package com.example.myaidl;

// Declare any non-default types here with import statements

interface IMusicAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);


     //申明可以被调用的接口
        void playMusic();
        void pauseMusic();
}
