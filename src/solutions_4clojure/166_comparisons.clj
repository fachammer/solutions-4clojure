(ns solutions-4clojure.166-comparisons)

(defn comparison [op a b]
  (cond
    (op a b) :lt
    (op b a) :gt
    :else :eq))
