# sift
Sift is a library for categorizing or filtering any text content. Some use cases of sift.
* It allows you to detect spam or no-spam emails.
* It lets you categorize documents under myriad of custom categories.

You just need to train it with seed texts of your category choices and it is smart enough to learn how distinguish given any text to it in future. It's internal workings depend on a supervised machine learning technique for training and classification. It uses Naive Bayes classifier for this purpose. For reference please follow the link.

* [Naive Bayes classifier](https://en.wikipedia.org/wiki/Naive_Bayes_classifier)

### Installation

Use it as a maven dependency.
* Step 1. Add the JitPack repository to your build file 
```sh
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
* Step 2. Add the dependency
```sh
	<dependency>
	    <groupId>com.github.joyghosh</groupId>
	    <artifactId>sift</artifactId>
	    <version>v1.0-beta</version>
	</dependency>
```

###	Demo
Please follow the link below to see a live demo of sift classifier which tags whether a content posted is a spam or not a spam on a best effort basis. It is a pre-compiled version of a trained classifier.

* [sift live demo](http://sift-joyghosh.rhcloud.com/sift/)

### License

The MIT License (MIT)

Copyright (c) 2016 Joy Ghosh

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.