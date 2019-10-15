(defproject clojusc/gremlin-utils "0.1.1"
  :description "Apache TinkerPop Gremlin Utility plugins/functions written in Clojure"
  :url "https://github.com/clojusc/gremlin-utils"
  :license {
    :name "Apache License, Version 2.0"
    :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :exclusions [
    [org.clojure/clojure]]
  :dependencies [
    [com.tinkerpop.gremlin/gremlin-groovy "2.6.0"]
    [org.clojure/clojure "1.10.1"]]
  :aot [
    clojusc.gremlin.plugins.util]
  :profiles {
    :ubercompile {
      :aot :all}
    :lint {
      :source-paths ^:replace ["src"]
      :test-paths ^:replace []
      :exclusions [
        [org.clojure/clojure]]
      :plugins [
        [jonase/eastwood "0.3.6"]
        [lein-ancient "0.6.15"]
        [lein-kibit "0.1.7"]]}
    :dev {
      :source-paths ["dev-resources/src"]
      :repl-options {
        :init-ns clojusc.gremlin.dev}
      :dependencies [
        [org.clojure/tools.nrepl "0.2.13"]]}
    :test {
      :dependencies [
        [org.clojure/tools.namespace "0.3.1"]]
      :plugins [
        [lein-ltest "0.4.0"]]
      :test-paths ["test"]
      :test-selectors {
        :default :unit
        :unit :unit
        :system :system
        :integration :integration}}}
  :aliases {
    ;; Dev
    "repl" ["do"
      ["clean"]
      ["repl"]]
    "ubercompile" ["with-profile" "+ubercompile" "compile"]
    ;; Linting and tests
    "check-vers" ["with-profile" "+lint" "ancient" "check" ":all"]
    "check-jars" ["with-profile" "+lint" "do"
      ["deps" ":tree"]
      ;["deps" ":plugin-tree"]
      ]
    "check-deps" ["do"
      ["check-jars"]
      ["check-vers"]]
    "kibit" ["with-profile" "+lint" "do"
      ["kibit"]]
    "eastwood" ["with-profile" "+lint" "eastwood" "{:namespaces [:source-paths]}"]
    "lint" ["do"
      ["kibit"]
      ;["eastwood"]
      ]
    "ltest" ["with-profile" "+test" "ltest"]
    ;; Build
    "build" ^{:doc "Perform build steps."} ["do"
      ["clean"]
      ["check-vers"]
      ["ubercompile"]
      ["clean"]
      ["lint"]
      ["ltest"]
      ["uberjar"]]})
