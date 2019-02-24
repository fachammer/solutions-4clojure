(ns solutions-4clojure.096-beauty-is-symmetry)

(defn symmetric-binary-tree? [tree]
  (let [flip (fn flip [node]
               (when-let [[value left right] node]
                 [value (flip right) (flip left)]))]
    (= tree (flip tree))))
