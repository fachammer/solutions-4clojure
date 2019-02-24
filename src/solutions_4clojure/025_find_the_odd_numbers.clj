(ns solutions-4clojure.025-find-the-odd-numbers)

(defn odd-numbers [coll]
  (filter odd? coll))
