## BucketLog

Simple logging system for FRC Java projects.

### Usage

A `Recordable` can be made of any type, as long as it supports `toString()`. Then, update the `Recordable` with new values, which are queued and saved in a csv file and any other `Recorder` type (websockets, NetworkTables, etc).
