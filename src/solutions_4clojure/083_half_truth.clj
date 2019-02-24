(ns solutions-4clojure.083-half-truth)

(defn half-truth [& args]
  (and (not (nil? (some true? args)))
       (not-every? true? args)))
