(ns solutions-4clojure.034-implement-range)

(defn my-range [start end]
  (take (- end start) (iterate inc start)))
