 .DEFAULT_GOAL := build-run

clean:
	make -C ./app clean

lint:
	make -C ./app lint

run-dist:
	make -C ./app run-dist

test:
	make -C ./app test

build:
	make -C ./app build

.PHONY: build

run-json:
	make -C ./app run-json

run-yaml:
	make -C ./app run-yaml

report:
	make -C ./app report