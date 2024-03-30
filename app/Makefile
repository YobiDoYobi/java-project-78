.DEFAULT_GOAL := build-run

clean:
	gradle clean

run-dist:
	./build/install/app/bin/app

lint:
	gradle checkstyleMain
	gradle checkstyleTest

build:
	make clean
	make lint
	make test
	gradle installDist

test:
	gradle test

run-json:
	./build/install/app/bin/app ./src/test/resources/file1.json ./src/test/resources/file2.json

run-yaml:
	./build/install/app/bin/app ./src/test/resources/file1.json ./src/test/resources/file2.json

report:
	gradle jacocoTestReport

.PHONY: build