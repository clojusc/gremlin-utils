(ns clojusc.gremlin.plugins.util
  (:require
    [clojure.string :as string]
    [clojure.java.shell :as shell]
    [clojusc.gremlin.util :as util])
  (:import
    (com.tinkerpop.gremlin.groovy.console ConsoleGroovy
                                          ConsoleIO
                                          ConsolePlugin)
    (org.apache.tinkerpop.gremlin.groovy.plugin AbstractGremlinPlugin)
    (java.util Map))
  (:gen-class
    :name clojusc.gremlin.plugins.Util
    :implements com.tinkerpop.gremlin.groovy.console.ConsolePlugin
    :extends org.apache.tinkerpop.gremlin.groovy.plugin.AbstractGremlinPlugin)

(def long-name "Clojusc Gremlin Utils Plugin")
(def plugin-name "tinkerpop.clojusc.util")

(defn -getName
  []
  plugin-name)

(defn -pluginTo
  [^ConsoleGroovy groovy ^ConsoleIO io ^Map args]
  (groovy.execute "import clojusc.gremlin.util")
  (io.write (format "Setup complete for '%s'" plugin-name))
