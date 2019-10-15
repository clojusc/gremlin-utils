(ns clojusc.gremlin.plugins.util
  (:require
    [clojure.string :as string]
    [clojure.java.shell :as shell]
    [clojusc.gremlin.util :as util])
  (:import
    (org.apache.tinkerpop.gremlin.jsr223 AbstractGremlinPlugin)
    (java.util Date Map))
  (:gen-class
    :name clojusc.gremlin.plugins.ClojuscGremlinUtilsPlugin
    :extends org.apache.tinkerpop.gremlin.jsr223.AbstractGremlinPlugin
    :methods
      [^:static [uuid2Long [String] Long]
       ^:static [uuid2TimeLong [String] Long]
       ^:static [uuid2RFC3339 [String] String]
       ^:static [uuid2TDate [String] java.util.Date]]))

(def long-name "Clojusc Gremlin Utils Plugin")
(def plugin-name "tinkerpop.clojusc.util")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;  Plugin Implementation Methods   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -getName
  []
  plugin-name)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;   Clojusc Util Static Methods   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -uuid2Long
  (^Long [^String uuid]
    (util/uuid->long (read-string uuid))))

(defn -uuid2TimeLong
  (^Long [^String uuid]
    (util/uuid->time-ns (read-string uuid))))

(defn -uuid2RFC3339
  (^String [^String uuid]
    (util/uuid->rfc3339 (read-string uuid))))

(defn -uuid2TDate
  (^Date [^String uuid]
    (util/uuid->time-date (read-string uuid))))
