import { Star16Regular, Star16Filled } from "@fluentui/react-icons";
import { useState } from "react";

export default function FavoriteStar() {
  const [favorite, setFavorite] = useState(false);

  return (
    <div style={{ display: "flex", justifyContent: "flex-end" }}>
      <div onClick={() => setFavorite(!favorite)} style={{ cursor: "pointer" }}>
        {favorite ? (
          <Star16Filled style={{ color: "#FFD700" }} />
        ) : (
          <Star16Regular />
        )}
      </div>
    </div>
  );
}
