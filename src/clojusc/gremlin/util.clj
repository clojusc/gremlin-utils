(ns clojusc.gremlin.util
  (:require
    [clojure.string :as string]
    [clojure.java.shell :as shell])
  (:import
    (java.text SimpleDateFormat)
    (java.util Date UUID)))

(def ns-in-ms 10000.0)
(def gregorian-base-as-unix -12219292800000)
(def rfc3339 "yyyy-MM-dd'T'HH:mm:ssZ")

(defn uuid
  "UUID doesn't create time-based UUIDs; the command line tool does, through."
  []
  (string/trim (:out (shell/sh "uuid"))))

(defn ns->ms
  [nanos]
  (/ nanos ns-in-ms))

(defn gregorian->unix-ms
  [greg-ns]
  (.longValue (+ (ns->ms greg-ns) gregorian-base-as-unix)))

(defn uuid->long
  [^String uuid]
  (bit-and
   (.getMostSignificantBits (UUID/fromString uuid))
   (Math/abs (.longValue 1e16))))

(defn uuid->time-ns
  [^String uuid]
  (.timestamp (UUID/fromString uuid)))

(defn uuid->time-date
  [^String uuid]
  (new Date (gregorian->unix-ms (uuid->time-ns uuid))))

(defn uuid->rfc3339
  [^String uuid]
  (.format (new SimpleDateFormat rfc3339) (uuid->time-date uuid)))
