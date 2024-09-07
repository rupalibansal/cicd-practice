import { useNavigate, useParams } from "react-router-dom";
import BlogPostForm from "../../components/BlogPostForm/BlogPostForm";
import { BlogPostFormData } from "../../components/BlogPostForm/schema";
import {
  BlogPostResponse,
  editBlogPost,
  getBlogPostById,
} from "../../services/blog-post-services";
import { useEffect, useState } from "react";

const EditBlogPostPage = () => {
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();

  const [post, setPost] = useState<BlogPostResponse>({
    id: 1,
    title: "default",
    content: "default",
    createdAt: "sometimestamp",
    updatedAt: "sometimestamp",
    category: "default",
  });

  const [isDataAvailable, setIsDataAvailable] = useState<boolean>(false);
  useEffect(() => {
    getBlogPostById(Number(id))
      .then((data) => {
        setPost(data);
        setIsDataAvailable(true);
      })
      .catch((e) => console.log(e));
  }, [id]);

  const onSubmit = async (data: BlogPostFormData) => {
    editBlogPost(Number(id), data)
      .then(() => navigate("/"))
      .catch((e) => console.log(e));
  };
  return (
    <>
      <h1>Edit a Blog Post</h1>
      {isDataAvailable && (
        <BlogPostForm onSubmit={onSubmit} initialValues={post} />
      )}
    </>
  );
};

export default EditBlogPostPage;
