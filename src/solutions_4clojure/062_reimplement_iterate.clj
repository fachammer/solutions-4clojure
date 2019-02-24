(ns solutions-4clojure.062-reimplement-iterate)

(defn my-iterate [f x]
  (reductions (fn [acc _] (f acc)) x (range)))
