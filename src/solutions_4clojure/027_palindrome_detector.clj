(ns solutions-4clojure.027-palindrome-detector)

(defn palindrome? [coll]
  (= (seq coll) (reverse coll)))
