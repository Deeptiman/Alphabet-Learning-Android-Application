LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := OdiaAlphabet
LOCAL_SRC_FILES := OdiaAlphabet.c
LOCAL_LDLIBS := -llog
include $(BUILD_SHARED_LIBRARY)
