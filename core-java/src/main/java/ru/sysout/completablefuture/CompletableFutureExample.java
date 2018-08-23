package ru.sysout.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {

	public static void runAsyncExample() throws InterruptedException, ExecutionException {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			System.out.println("I'll run in a separate thread than the main thread.");
		});
		// Block and wait for the future to complete
		future.get();
	}

	public static void supplyAsyncExample() throws InterruptedException, ExecutionException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Result of the asynchronous computation";
		});
		// Block and wait for the future to complete
		System.out.println(future.get());
	}

	public static void thenApplyExample() throws InterruptedException, ExecutionException {
		CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			System.out.println(Thread.currentThread().getName());
			return "Rajeev";
		});

		// Attach a callback to the Future using thenApply()
		CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
			System.out.println(Thread.currentThread().getName());
			return "Hello " + name;
		});
		System.out.println(Thread.currentThread().getName()+" go further");

		// Block and wait for the future to complete
		System.out.println(Thread.currentThread().getName()+" "+greetingFuture.get());
	}

	public static void thenApplyChainExample() throws InterruptedException, ExecutionException {
		CompletableFuture<String> welcomeText  = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			System.out.println(Thread.currentThread().getName());
			return "Rajeev";
		}).thenApply(name -> {
			System.out.println(Thread.currentThread().getName());
			return "Hello " + name;
		
		}).thenApply(greeting -> {
			System.out.println(Thread.currentThread().getName());
			return greeting + ", Welcome to the CalliCoder Blog";
		});
		System.out.println(Thread.currentThread().getName()+" go further");
		
		// Block and wait for the future to complete
		System.out.println(Thread.currentThread().getName()+" "+welcomeText .get());
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// runAsyncExample();
		// supplyAsyncExample();
		//thenApplyExample();
		thenApplyChainExample();
		
	}

}
