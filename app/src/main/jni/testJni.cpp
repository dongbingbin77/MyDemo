//
// Created by 董冰彬 on 2017/2/8.
//

#include "testJni.h"
void changeFloatByCpp(float *inputFloatArray,int ArraySize){
    for(int i=0;i<ArraySize;i++){
        inputFloatArray[i] *=2.0f;
    }
    return;
}