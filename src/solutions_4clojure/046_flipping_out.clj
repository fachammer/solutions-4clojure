(ns solutions-4clojure.046-flipping-out)

(defn flip-arguments [f]
  (fn [& args]
    (apply f (reverse args))))
