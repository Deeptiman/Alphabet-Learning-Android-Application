#include "stdio.h"
#include "math.h"
#include <android/log.h>
#include "com_odia_alphabet_application_DrawApp.h"
#define APPNAME "MyApp"

jdouble getDistance(jdouble x1, jdouble x2, jdouble y1, jdouble y2);


JNIEXPORT jstring JNICALL Java_com_odia_alphabet_application_DrawApp_getEmail
        (JNIEnv *env, jobject o) {

    char msg[100] = "odiaalphabet@gmail.com";
    jstring result;

    result = (*env)->NewStringUTF(env, msg);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_odia_alphabet_application_DrawApp_getPassword
        (JNIEnv *env, jobject o) {

    char msg[100] = "MyTest@#432";
    jstring result;

    result = (*env)->NewStringUTF(env, msg);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_odia_alphabet_security_decrypt_DecryptHelper_getInnerKey
        (JNIEnv *env, jobject o) {

    char msg[1000] = "a49bf0bf51e94ca60e7a219945bc2c77507139f9c5df89a09e2c671c2005fc53ec7601d20dae4bbeae253a036cf652a666bd2704a548d6a9013283c3e3afbc78202711ed69b3d70ccf372ee80c42cca22a4f18fed677cc30cafe06f100301e934160bfc80f8d4cdf0d39ca99305159cf06bd0ffa6787d4daff6ab0593bf2772f17d948b94fbaa73b27967bd57d2775ced6774f8b3b5e200d599b66aaf872956f15e74b1ccd87e6ecabc7ed50298ac94a75c50a731c3c77f7be07bfb7d0efb050cb4a3ea467e1bf9ead81b4ffb43384e36055c4acdd75825e08f1afebaa134872b9d80bdd00bf2b9fa6d688c217a2c15a6eee349f6060fa555eacacf7ef44b085";
    jstring result;

    result = (*env)->NewStringUTF(env, msg);
    return result;
}


JNIEXPORT jstring JNICALL Java_com_odia_alphabet_security_decrypt_DecryptHelper_getOutterKey
        (JNIEnv *env, jobject o) {

    char msg[1000] = "3f36afa2e4e00ff6a2cbad2407198ea1172933b25caa08f39c834c2d3e0497b2216ed7448c0d9053252ba172a43aac2aabe745880f177923bc60287bdc3a93c00abc325098eb9dc633001cf9cdd800784540ef86c56aceaffc5341977c5286bb00521bbd1f84aabd5b219e696376dee43cf59dfd1207f4b81d02e7e71e34f02c9da5208ec23af129f6427a8b2faea207a8fd028ad0196eea97414c251bd82bef0c740d231c171f7c47ebd2223061223ce2962f09c88eb3f6a044ffa924c5a4d44f82a1b26bb629b4f35a1b6c12764a27a5f1a0232cdf5f3f66d9f08408c041502c37ec6ce974395a21d25610f857d0c17c0c6b6ba8cc36879fe5386b0b46c12c";
    jstring result;

    result = (*env)->NewStringUTF(env, msg);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_odia_alphabet_security_decrypt_DecryptHelper_getRSAPublicKey
        (JNIEnv *env, jobject o) {

    char msg[100] = "sdjksdjsdsdjksfjksdjsfwiewerwi";
    jstring result;

    result = (*env)->NewStringUTF(env, msg);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_odia_alphabet_security_decrypt_DecryptHelper_getRSAPrivateKey
        (JNIEnv *env, jobject o) {

    char msg[2000] = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDHTy3ScXcxG14oJC5bz9Y/L0A+\n"
            "ietZXs0pK1SWRcfS2LrBlAmJ1vxaKjPwaA37up3aH61ffyH4lMsVuBFFr4bLzCxHCls8iBAU4W0h\n"
            "AXkp93bXB6IWFKV1AMc8fRXx//v7JHYO7XzonAKSLFhWVsYlQUBZ5Lt0VPicKVLVDbaRylpeJ2LD\n"
            "sQoTFQqeRDHSTjEylMlCk+0ZDiugU8bq272vE3jebfkkvg5XfQCTuFryx8QLWQXentN1MsPdMe+z\n"
            "jseKzx+5KEzLYEza9D6NYjmc9RMJBaBbNF+3RFwKGi6sLCOQDbMLiLGPFrAXzwEt5t1L9s1E/vE/\n"
            "pwjnF5jdxSXHAgMBAAECggEAVmvFwadDeH6WcC9oDpVrH9CcOcHR09mgegZ40gQF5i3lpMRM8oDO\n"
            "wUXlaYuk7I75nTf5FBxrHX9fI9bBXocCmuG4HUGnV/LjjjpE+HfMEt9jn0+G/64KJFanDVprpphJ\n"
            "qZcgvc5KSA5Fqx4hW6aamlLV1WSxx3qEping6dLdZA8mdmmJKE8SAXhFIMC+j0CS6hAJzshkvqZC\n"
            "ShTJo1zXoWL/Qaj3g7GIajuoQQjKhwDA4UEcV4c/TWUGyFIzZ6A3s4NiTQbkWsyCAsv+IQT3sUNX\n"
            "02Ceu1s5Ico+eMwJYR0Vm0E+F5DRmLRtSvS4RmcZ6s21QI6jGBKolJPo1fIpaQKBgQDzpjTrdWFk\n"
            "sRwUR8qpfc6xiKOekAf2BL65AsTKKKtR6bXSfrJNA751jIU6E4cjS5QuQ4ZcWC/0YXiBRLXO612z\n"
            "0wl3LxGMMBSoWC4iX3+mILvPozr2lfhtJ1PO0NUuGPAu4O8b4i04RqZcCk5DIF2adsH8U7QpJ8u6\n"
            "VbLt6VP0WwKBgQDRaZSeC3AC7JGZo111aW7brpBem226K9Kv2C+5EyxG7GB2vTSMp9TCcS/E33oO\n"
            "foijH1MMeIlQPQwoFHZv4T+PX6OpRkBuoVwMlePpV9QjRVn2ykwwbG+XBlsDlqrFmAgasptypFzg\n"
            "rhVpolXb43igzakQV+SinFKctQuTvaIgBQKBgQDYvYKX7nwr8W1UESUUL0j+HtnGRMQAMupidDcQ\n"
            "yqrUHD3VOUVtQyZbNRyStCpj2GjB2umcHgrSrx5eh/vfTPq268Wg0ysd9Ot7ca3gf+lyo+3OYJsk\n"
            "Hfa3SuO/t3/cGO0YFpo9yK6HQDne6IAEMLdzn68iwmcisTcG1BNulkBLvwKBgDpefmEq+Panr9Gu\n"
            "Gk+uqoqq9myYUBZTPq9qx6qDcJT4L88I38vQv9nOqKzsSV4e4MDu6zwPoN5vlaLaSUTX+NpiowB8\n"
            "p1ruuPR9FwCkTWnjW7cRHfllUBk9WUL/zOu0e5XNcMyciZLKkwdViy72EVpo2JXImd7FSbWKNQbi\n"
            "FX1tAoGBAJl3cv7A0OUIG80LPuChkjv5DAFejhuXqfgC2XJv7gDxz3B77qDrw+bYYEdec7QV32e4\n"
            "rqD9BgTYZB1QmZRJhFIzfoA3r4bOmETTcblC/ANKGpb4QLxW++TY4+dp719FUlT4U/Jblr1cn681\n"
            "C9vtBhQnlOJVWH5vCXaqST2LjLO/";
    jstring result;

    result = (*env)->NewStringUTF(env, msg);
    return result;
}


jint minIndex = 0, ctouchPoint = 0;
jdouble tempD = 0, tempminD = 0;
jint minCount = 0, tempMin = 0, tempCount = 0;

JNIEXPORT jint JNICALL Java_com_odia_alphabet_draw_GetTouchPoint_getMinTouchPoint(JNIEnv *env,
                  jobject o,
                  jobject alphabetXY,
                  jobject alphabetNextXY,
                  jint pointX,
                  jint pointY,
                  jint i,
                  jdouble minD) {


    jclass alphabetXYObj = (*env)->GetObjectClass(env, alphabetXY);
    jfieldID circularShapeId = (*env)->GetFieldID(env, alphabetXYObj, "circularShape", "I");
    jint circularShape = (*env)->GetIntField(env, alphabetXY, circularShapeId);

    jfieldID cXId = (*env)->GetFieldID(env, alphabetXYObj, "x", "I");
    jfieldID cYId = (*env)->GetFieldID(env, alphabetXYObj, "y", "I");

    jclass alphabetNxtXYObj = (*env)->GetObjectClass(env, alphabetNextXY);

    jfieldID nxtXId = (*env)->GetFieldID(env, alphabetNxtXYObj, "x", "I");
    jfieldID nxtYId = (*env)->GetFieldID(env, alphabetNxtXYObj, "y", "I");

    jint cX = (jfloat) (*env)->GetIntField(env, alphabetXY, cXId);
    jint cY = (jfloat) (*env)->GetIntField(env, alphabetXY, cYId);

    jint nxtX = (jfloat) (*env)->GetIntField(env, alphabetNextXY, nxtXId);
    jint nxtY = (jfloat) (*env)->GetIntField(env, alphabetNextXY, nxtYId);

    jint midX = (cX + nxtX) / 2;
    jint midY = (cY + nxtY) / 2;

    jdouble d1 = getDistance(midX, pointX, midY, pointY);
    jdouble d3 = getDistance(cX, nxtX, cY, nxtY);

    if (d1 < d3) {
        if (minD > d1) {
            minD = d1;
            minIndex = i;
        }
    } else if (tempD > d1 && d3 == 0 && i - ctouchPoint <= 1) {
        minIndex = i;
    }

    tempD = d1;

    if (circularShape == 1) {
        if (minD == tempminD) {
            minCount++;
            tempMin = minIndex;
            tempCount = minCount;
        } else {
            if (tempCount > minCount) {
                minIndex = tempMin;
            }
            minCount = 0;
        }
        tempminD = minD;
    }

    return minIndex;
}


JNIEXPORT jint JNICALL Java_com_odia_alphabet_draw_GetTouchPoint_getTouchPoint(JNIEnv *env,
               jobject o,
               jobjectArray alphabetXYies,
               jint pointX,
               jint pointY) {

    jint i = 0;

    jobject alphabetXY1 = (jobject) (*env)->GetObjectArrayElement(env, alphabetXYies, 0);
    jclass alphabetXYObj1 = (*env)->GetObjectClass(env, alphabetXY1);
    jfieldID initialStatusId = (*env)->GetFieldID(env, alphabetXYObj1, "status", "I");
    jint initialStatus = (*env)->GetIntField(env, alphabetXY1, initialStatusId);

    if (initialStatus == 1) {
        minIndex = 0;
    }

    jfieldID cXiId = (*env)->GetFieldID(env, alphabetXYObj1, "x", "I");
    jfieldID cYiId = (*env)->GetFieldID(env, alphabetXYObj1, "y", "I");

    jint cXi = (*env)->GetIntField(env, alphabetXY1, cXiId);
    jint cYi = (*env)->GetIntField(env, alphabetXY1, cYiId);

    jobject alphabetXY2 = (jobject) (*env)->GetObjectArrayElement(env, alphabetXYies, 1);
    jclass alphabetXYObj2 = (*env)->GetObjectClass(env, alphabetXY2);
    jfieldID cXi2Id = (*env)->GetFieldID(env, alphabetXYObj2, "x", "I");
    jfieldID cYi2Id = (*env)->GetFieldID(env, alphabetXYObj2, "y", "I");

    jint cXi2 = (*env)->GetIntField(env, alphabetXY2, cXi2Id);
    jint cYi2 = (*env)->GetIntField(env, alphabetXY2, cYi2Id);

    jfloat midX1 = (cXi + cXi2) / 2;
    jfloat midY1 = (cYi + cYi2) / 2;

    jdouble minD = getDistance(midX1, pointX, midY1, pointY);
    jdouble tempD = 0, tempminD = 0;

    jint nextIndex = 0, minCount = 0, tempMin = 0, tempCount = 0;
    jint totalXY = (*env)->GetArrayLength(env, alphabetXYies);

    for (i = 0; i < totalXY; i++) {

        jobject alphabetXY = (jobject) (*env)->GetObjectArrayElement(env, alphabetXYies, i);
        jclass alphabetXYObj = (*env)->GetObjectClass(env, alphabetXY);
        jfieldID circularShapeId = (*env)->GetFieldID(env, alphabetXYObj, "circularShape", "I");
        jint circularShape = (*env)->GetIntField(env, alphabetXY, circularShapeId);

        jfieldID xyId = (*env)->GetFieldID(env, alphabetXYObj, "aId", "I");
        jint xy = (*env)->GetIntField(env, alphabetXY, xyId);

        jfieldID statusId = (*env)->GetFieldID(env, alphabetXYObj, "status", "I");
        jint status = (*env)->GetIntField(env, alphabetXY, statusId);
        if (status == 1)
            continue;

        nextIndex = i + 1;

        if (i == totalXY - 1) {
            nextIndex = i;
        }

        jfieldID cXId = (*env)->GetFieldID(env, alphabetXYObj, "x", "I");
        jfieldID cYId = (*env)->GetFieldID(env, alphabetXYObj, "y", "I");

        jobject alphabetNextXY = (jobject) (*env)->GetObjectArrayElement(env, alphabetXYies,
         nextIndex);
        jclass alphabetNxtXYObj = (*env)->GetObjectClass(env, alphabetNextXY);

        jfieldID nxtXId = (*env)->GetFieldID(env, alphabetNxtXYObj, "x", "I");
        jfieldID nxtYId = (*env)->GetFieldID(env, alphabetNxtXYObj, "y", "I");

        jint cX = (jfloat) (*env)->GetIntField(env, alphabetXY, cXId);
        jint cY = (jfloat) (*env)->GetIntField(env, alphabetXY, cYId);

        jint nxtX = (jfloat) (*env)->GetIntField(env, alphabetNextXY, nxtXId);
        jint nxtY = (jfloat) (*env)->GetIntField(env, alphabetNextXY, nxtYId);

        jint midX = (cX + nxtX) / 2;
        jint midY = (cY + nxtY) / 2;

        jdouble d1 = getDistance(midX, pointX, midY, pointY);
        jdouble d3 = getDistance(cX, nxtX, cY, nxtY);

        if (d1 < d3) {
            if (minD > d1) {
                minD = d1;
                minIndex = i;
            }
        } else if (tempD > d1 && d3 == 0 && i - ctouchPoint <= 1) {
            minIndex = i;
        }

        tempD = d1;

        if (circularShape == 1) {
            if (minD == tempminD) {
                minCount++;
                tempMin = minIndex;
                tempCount = minCount;
            } else {
                if (tempCount > minCount) {
                    minIndex = tempMin;
                }
                minCount = 0;
            }
            tempminD = minD;
        }

        /*__android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of xy is %d", xy);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of cX is %d", cX);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of cY is %d", cY);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of nxtX is %d", nxtX);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of nxtY is %d", nxtY);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of midX is %d", midX);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of midY is %d", midY);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of pointX is %d", pointX);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of pointY is %d", pointY);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of d1 is %lf", d1);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of d3 is %lf", d3);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : The value of minIndex is %d", minIndex);*/
    }

    return minIndex;
}

JNIEXPORT jint Java_com_odia_alphabet_draw_GetTouchPoint_getMinIndex(JNIEnv *env, jobject o) {
    return minIndex;
}

JNIEXPORT jint Java_com_odia_alphabet_draw_GetTouchPoint_getCTouchPoint(JNIEnv *env, jobject o) {
    return ctouchPoint;
}

JNIEXPORT void Java_com_odia_alphabet_draw_GetTouchPoint_setTouchPoint(JNIEnv *env, jobject o,
       jint jcTouchPoint) {
    ctouchPoint = jcTouchPoint;
}

JNIEXPORT jdouble getDistance(jdouble x1, jdouble x2,
                              jdouble y1, jdouble y2) {

    return (jdouble) sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
}

int circularMinIndex = 0;
double minD = 0, minTempD = 0;

JNIEXPORT int Java_com_odia_alphabet_draw_GetTouchPoint_getCircularminIndex(JNIEnv *env, jobject o,
            double minDValue,
            int minIndex) {

    if (minDValue == tempminD) {
        minCount++;
        tempMin = minIndex;
        tempCount = minCount;
    } else {
        if (tempCount > minCount) {
            minIndex = tempMin;
        }
        minCount = 0;
    }
    tempminD = minDValue;
    circularMinIndex = minIndex;
    return circularMinIndex;
}

JNIEXPORT double Java_com_odia_alphabet_draw_GetTouchPoint_getminD(JNIEnv *env, jobject o,
   float pointX, float pointY,
   float x1, float x2,
   float y1, float y2) {

    float midX = (x1 + x2) / 2;
    float midY = (y1 + y2) / 2;

    minD = sqrt(pow(midX - pointX, 2) + pow(midY - pointY, 2));
    //__android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : Save minD is %lf", minD);
    return minD;
}

int minTouchIndex = 0;

JNIEXPORT int Java_com_odia_alphabet_draw_GetTouchPoint_getminIndex(JNIEnv *env, jobject o,
    double d1, double d3,
    int i, int ctouchPoint) {

    if (d1 < d3) {
        if (minD > d1) {
            minD = d1;
            minTouchIndex = i;
        }
    } else if (minTempD > d1 && d3 == 0 && i - ctouchPoint <= 1) {
        minTouchIndex = i;
    }
    minTempD = d1;

    /*__android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : i is %d", i);
    __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : d1 is %lf", d1);
    __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : d3 is %lf", d3);
    __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : minD is %lf", minD);
    __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : tempD is %lf", minTempD);
    __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : ctouchPoint is %d", ctouchPoint);
    __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "Native : minIndex is %d", minTouchIndex);*/

    return minTouchIndex;
}





JNIEXPORT double Java_com_odia_alphabet_draw_GetTouchPoint_getD1(JNIEnv *env, jobject o, float x1,
 float x2, float y1, float y2,
 float pointX, float pointY) {

    float midX = (x1 + x2) / 2;

    float midY = (y1 + y2) / 2;

    return sqrt(pow(midX - pointX, 2) + pow(midY - pointY, 2));
}


JNIEXPORT double Java_com_odia_alphabet_draw_GetTouchPoint_getD3(JNIEnv *env, jobject o, float x1,
 float x2,
 float y1, float y2) {

    return (double) sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
}

JNIEXPORT void Java_com_odia_alphabet_draw_GetTouchPoint_resetDraw(JNIEnv *env, jobject o) {
    //__android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "ResetDraw");
    minTouchIndex = 0;
    minD = 0;
    minTempD = 0;
    minCount = 0;
    tempminD = 0;
    tempMin = 0;
    tempCount = 0;
    circularMinIndex = 0;
}

