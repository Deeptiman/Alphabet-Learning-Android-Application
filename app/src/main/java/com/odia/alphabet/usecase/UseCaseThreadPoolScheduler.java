/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.odia.alphabet.usecase;



import java.util.concurrent.*;

/**
 * Executes asynchronous tasks using a {@link ThreadPoolExecutor}.
 * <p>
 * See also {@link Executors} for a list of factory methods to create common
 * {@link ExecutorService}s for different scenarios.
 */
public class UseCaseThreadPoolScheduler implements UseCaseScheduler {

    //private final Handler mHandler = new Handler();

    public static final int POOL_SIZE = 200;

    public static final int MAX_POOL_SIZE = 210;

    public static final int TIMEOUT = 30;

    ThreadPoolExecutor mThreadPoolExecutor;
    public UseCaseThreadPoolScheduler() {
        System.out.println("UseCaseThreadPoolScheduler");
        mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(POOL_SIZE));
    }

    @Override
    public void execute(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    @Override
    public <V extends UseCase.ResponseValue,T extends UseCase.TaskFinished> void notifyResponse(final V response,
            final UseCase.UseCaseCallback<V,T> useCaseCallback) {
        mThreadPoolExecutor.shutdown();
        useCaseCallback.onSuccess(response);
    }

    @Override
    public <V extends UseCase.ResponseValue,T extends UseCase.TaskFinished> void onError(
            final UseCase.UseCaseCallback<V,T> useCaseCallback) {

        useCaseCallback.onError();
    }
}
