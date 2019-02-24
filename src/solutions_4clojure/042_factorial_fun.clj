(ns solutions-4clojure.042-factorial-fun)

(defn factorial [n]
  (if (= n 0)
    1
    (* n (factorial (dec n)))))
