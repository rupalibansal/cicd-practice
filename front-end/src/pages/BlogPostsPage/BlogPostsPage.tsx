import { useEffect, useState } from "react";
import {
  BlogPostResponse,
  getAllBlogPosts,
  deleteBlogPostById,
} from "../../services/blog-post-services";
import { BlogPost } from "../../components/BlogPost";
import { useNavigate } from "react-router-dom";

const BlogPostsPage = () => {
  const [posts, setPosts] = useState<BlogPostResponse[]>([]);
  useEffect(() => {
    getAllBlogPosts()
      .then((data) => setPosts(data))
      .catch((e) => console.log(e));
  }, []);

  const navigate = useNavigate();

  const handleCreatePost = () => {
    navigate("/posts/new");
  };

  const handleDelete = async (id: number) => {
    const confirmed = confirm("Are you sure you want to delete");
    if (!confirmed) {
      return;
    }
    console.log("post to delete with id ", id);
    const isDeleted = await deleteBlogPostById(id).catch((e) => {
      console.log(e);
      return false;
    });

    if (isDeleted) {
      const newPosts = posts.filter((post) => post.id !== id);
      console.log("new posts", newPosts);
      setPosts(newPosts);
    }
  };
  return (
    <>
      <button onClick={handleCreatePost}>Create Blog Post</button>
      <BlogPost posts={posts} onDelete={handleDelete} />
    </>
  );
};
export default BlogPostsPage;
