(ns solutions-4clojure.134-a-nil-key)

(defn contains-nil-value-key? [key m]
  (and (contains? m key) (= nil (m key))))
