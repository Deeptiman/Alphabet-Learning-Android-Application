#include "stdio.h"
#include "math.h"
#include <android/log.h>
#include "com_odia_alphabet_application_DrawApp.h"

#define APPNAME "MyApp"

jdouble getDistance(jdouble x1, jdouble x2, jdouble y1, jdouble y2);


JNIEXPORT jstring JNICALL Java_com_odia_alphabet_application_DrawApp_getEmail
        (JNIEnv * env,jobject o) {

    char msg[100] = "odiaalphabet@gmail.com";
    jstring result;

    result = (env)->NewStringUTF( msg);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_odia_alphabet_application_DrawApp_getPassword
        (JNIEnv * env,jobject o) {

    char msg[100] = "MyTest@#432";
    jstring result;

    result = (env)->NewStringUTF( msg);
    return result;
}

JNIEXPORT void JNICALL Java_com_odia_alphabet_draw_GetTouchPoint_readAlphabetXY(JNIEnv *env,
                                                                                jobject o,
                                                                                jobjectArray alphabetXYies){
    jobject alphabetXYObj = (jobject) (env)->GetObjectArrayElement( alphabetXYies, 0);
    jclass listClass = env->FindClass( "java/util/ArrayList" );
    jint totalXY = (env)->GetArrayLength( alphabetXYies);

    __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of totalXY is %d", totalXY);
}

jint minIndex = 0, ctouchPoint = 0;

JNIEXPORT jint JNICALL Java_com_odia_alphabet_draw_GetTouchPoint_getTouchPoint(JNIEnv *env,
                                                                               jobject o,
                                                                               jobjectArray alphabetXYies,
                                                                               jint pointX,
                                                                               jint pointY) {

    jint i = 0;

    jobject alphabetXY1 = (jobject) (env)->GetObjectArrayElement( alphabetXYies, 0);
    jclass alphabetXYObj1 = (env)->GetObjectClass( alphabetXY1);
    jfieldID initialStatusId = (env)->GetFieldID( alphabetXYObj1, "status", "I");
    jint initialStatus = (env)->GetIntField( alphabetXY1, initialStatusId);

    if (initialStatus == 1) {
        minIndex = 0;
    }

    jfieldID cXiId = (env)->GetFieldID( alphabetXYObj1, "x", "I");
    jfieldID cYiId = (env)->GetFieldID( alphabetXYObj1, "y", "I");

    jint cXi = (env)->GetIntField( alphabetXY1, cXiId);
    jint cYi = (env)->GetIntField( alphabetXY1, cYiId);

    jobject alphabetXY2 = (jobject) (env)->GetObjectArrayElement( alphabetXYies, 1);
    jclass alphabetXYObj2 = (env)->GetObjectClass( alphabetXY2);
    jfieldID cXi2Id = (env)->GetFieldID( alphabetXYObj2, "x", "I");
    jfieldID cYi2Id = (env)->GetFieldID( alphabetXYObj2, "y", "I");

    jint cXi2 = (env)->GetIntField( alphabetXY2, cXi2Id);
    jint cYi2 = (env)->GetIntField( alphabetXY2, cYi2Id);

    jfloat midX1 = (cXi + cXi2) / 2;
    jfloat midY1 = (cYi + cYi2) / 2;

    jdouble minD = getDistance(midX1, pointX, midY1, pointY);
    jdouble tempD = 0, tempminD = 0;

    jint nextIndex = 0, minCount = 0, tempMin = 0, tempCount = 0;
    jint totalXY = (env)->GetArrayLength( alphabetXYies);

    for (i = 0; i < totalXY; i++) {

        jobject alphabetXY = (jobject) (env)->GetObjectArrayElement( alphabetXYies, i);
        jclass alphabetXYObj = (env)->GetObjectClass( alphabetXY);
        jfieldID circularShapeId = (env)->GetFieldID( alphabetXYObj, "circularShape", "I");
        jint circularShape = (env)->GetIntField( alphabetXY, circularShapeId);

        jfieldID xyId = (env)->GetFieldID( alphabetXYObj, "aId", "I");
        jint xy = (env)->GetIntField( alphabetXY, xyId);

        jfieldID statusId = (env)->GetFieldID( alphabetXYObj, "status", "I");
        jint status = (env)->GetIntField( alphabetXY, statusId);
        if (status == 1)
            continue;

        nextIndex = i + 1;

        if (i == totalXY - 1) {
            nextIndex = i;
        }

        jfieldID cXId = (env)->GetFieldID( alphabetXYObj, "x", "I");
        jfieldID cYId = (env)->GetFieldID( alphabetXYObj, "y", "I");

        jobject alphabetNextXY = (jobject) (env)->GetObjectArrayElement( alphabetXYies, nextIndex);
        jclass alphabetNxtXYObj = (env)->GetObjectClass( alphabetNextXY);

        jfieldID nxtXId = (env)->GetFieldID( alphabetNxtXYObj, "x", "I");
        jfieldID nxtYId = (env)->GetFieldID( alphabetNxtXYObj, "y", "I");

        jint cX = (jfloat) (env)->GetIntField( alphabetXY, cXId);
        jint cY = (jfloat) (env)->GetIntField( alphabetXY, cYId);

        jint nxtX = (jfloat) (env)->GetIntField( alphabetNextXY, nxtXId);
        jint nxtY = (jfloat) (env)->GetIntField( alphabetNextXY, nxtYId);

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

        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of xy is %d", xy);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of cX is %d", cX);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of cY is %d", cY);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of nxtX is %d", nxtX);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of nxtY is %d", nxtY);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of midX is %d", midX);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of midY is %d", midY);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of pointX is %d", pointX);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of pointY is %d", pointY);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of d1 is %lf", d1);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of d3 is %lf", d3);
        __android_log_print(ANDROID_LOG_VERBOSE, APPNAME, "The value of minIndex is %d", minIndex);
    }

    return minIndex;
}

JNIEXPORT jint Java_com_odia_alphabet_draw_GetTouchPoint_getMinIndex(JNIEnv *env,jobject o){
    return minIndex;
}

JNIEXPORT jint Java_com_odia_alphabet_draw_GetTouchPoint_getCTouchPoint(JNIEnv *env,jobject o){
    return ctouchPoint;
}

JNIEXPORT void Java_com_odia_alphabet_draw_GetTouchPoint_resetDraw(JNIEnv *env,jobject o) {
    minIndex = 0;
    ctouchPoint = 0;
}

JNIEXPORT void Java_com_odia_alphabet_draw_GetTouchPoint_setTouchPoint(JNIEnv *env,jobject o,
                                                                       jint jcTouchPoint) {
    ctouchPoint = jcTouchPoint;
}

JNIEXPORT jdouble getDistance(jdouble x1, jdouble x2,
                              jdouble y1, jdouble y2) {

    return (jdouble) sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
}
