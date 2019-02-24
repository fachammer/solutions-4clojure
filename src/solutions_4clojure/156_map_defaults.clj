(ns solutions-4clojure.156-map-defaults)

(defn default-map [default-value map-keys]
  (zipmap map-keys (repeat default-value)))
