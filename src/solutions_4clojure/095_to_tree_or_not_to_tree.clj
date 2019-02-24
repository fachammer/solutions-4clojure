(ns solutions-4clojure.095-to-tree-or-not-to-tree)

(defn binary-tree? [coll]
  (or (nil? coll)
      (and (sequential? coll)
           (= 3 (count coll))
           (binary-tree? (nth coll 1))
           (binary-tree? (nth coll 2)))))
