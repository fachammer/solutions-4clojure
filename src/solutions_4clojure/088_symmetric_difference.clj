(ns solutions-4clojure.088-symmetric-difference)

(defn symmetric-difference [a b]
  (set (concat
        (filter (complement b) a)
        (filter (complement a) b))))
