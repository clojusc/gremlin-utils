(ns ^:unit clojusc.gremlin.util-test
  (:require
    [clojure.test :refer :all]
    [clojusc.gremlin.util :as util]))

(def test-uuid "89c79390-eece-11e9-9657-fbfd92a6069a")

(deftest gregorian->unix-ms
  (is (= 1571090645374
         (util/gregorian->unix-ms 137903834453742480))))

(deftest uuid->long
  (is (= 1858076672
         (util/uuid->long test-uuid))))

(deftest uuid->time-ns
  (is (= 137903834453742480
         (util/uuid->time-ns test-uuid))))

(deftest uuid->time-date
  (is (= #inst "2019-10-14T22:04:05.374-00:00"
         (util/uuid->time-date test-uuid))))

(deftest uuid->rfc3339
  (is (= "2019-10-14T17:04:05-0500"
         (util/uuid->rfc3339 test-uuid))))
