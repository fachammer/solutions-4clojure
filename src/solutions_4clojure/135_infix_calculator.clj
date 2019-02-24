(ns solutions-4clojure.135-infix-calculator)

(defn infix [val & args]
  (reduce (fn [result [op arg]] (op result arg)) val (partition 2 args)))
