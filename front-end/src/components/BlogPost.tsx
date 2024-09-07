import { BlogPostResponse } from "../services/blog-post-services";
import { useNavigate } from "react-router-dom";
import moment from "moment";

interface BlogPostProps {
  posts: BlogPostResponse[];
  onDelete: (id: number) => Promise<unknown>;
}

export const BlogPost = ({ posts, onDelete }: BlogPostProps) => {
  const navigate = useNavigate();

  const handleDeletePost = (id: number) => {
    onDelete(id);
  };

  const handleEditPost = (id: number) => {
    navigate(`/posts/${id}/edit`);
  };

  return (
    <>
      {posts.map((post: BlogPostResponse) => (
        <div key={post.id}>
          <h2>{post.title}</h2>
          <h3>{post.category}</h3>
          <h4>
            Last Edited: {moment(post.updatedAt).format("DD/MM/YYYY h:mmA")}
          </h4>
          <p>{post.content}</p>

          <button
            onClick={() => {
              handleEditPost(post.id);
            }}
          >
            Edit Post
          </button>
          <button
            onClick={() => {
              handleDeletePost(post.id);
            }}
          >
            Delete Post
          </button>
        </div>
      ))}
    </>
  );
};
