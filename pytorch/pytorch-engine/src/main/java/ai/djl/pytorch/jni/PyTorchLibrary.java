/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ai.djl.pytorch.jni;

import java.nio.ByteBuffer;

/** A class containing utilities to interact with the PyTorch Engine's JNI layer. */
@SuppressWarnings("MissingJavadocMethod")
public final class PyTorchLibrary {

    static {
        System.loadLibrary("djl_torch"); // NOPMD
    }

    public static final PyTorchLibrary LIB = new PyTorchLibrary();

    private PyTorchLibrary() {}

    public native long[] torchSizes(Pointer handle);

    public native ByteBuffer torchDataPtr(Pointer handle);

    public native int torchDType(Pointer handle);

    public native int[] torchDevice(Pointer handle);

    public native Pointer torchEmpty(
            long[] shape, int dType, int layout, int[] device, boolean requiredGrad);

    public native Pointer torchZeros(
            long[] shape, int dType, int layout, int[] device, boolean requiredGrad);

    public native Pointer torchOnes(
            long[] shape, int dType, int layout, int[] device, boolean requiredGrad);

    public native Pointer torchFromBlob(
            ByteBuffer data,
            long[] shape,
            int dType,
            int layout,
            int[] device,
            boolean requiredGrad);

    public native Pointer moduleLoad(String path);

    public native void moduleEval(Pointer handle);

    public native Pointer moduleForward(Pointer moduleHandle, Pointer[] IValuePointers);

    public native Pointer iValueCreateFromTensor(Pointer tensorHandle);

    public native Pointer iValueToTensor(Pointer iValueHandle);
}
