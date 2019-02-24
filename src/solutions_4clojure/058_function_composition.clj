(ns solutions-4clojure.058-function-composition)

(defn my-comp [& functions]
  (fn [& args]
    (reduce (fn [val f] (f val))
            (apply (last functions) args)
            (rest (reverse functions)))))
