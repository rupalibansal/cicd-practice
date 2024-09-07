import { useForm } from "react-hook-form";
import { BlogPostFormData, schema } from "./schema";
import { zodResolver } from "@hookform/resolvers/zod";
import classes from "./BlogPostForm.module.scss";

interface BlogPostFormProps {
  onSubmit: (data: BlogPostFormData) => unknown;
  initialValues?: BlogPostFormData;
}

const BlogPostForm = ({ onSubmit, initialValues }: BlogPostFormProps) => {
  const {
    reset,
    register,
    formState: { errors, isSubmitSuccessful },
    handleSubmit,
  } = useForm<BlogPostFormData>({
    resolver: zodResolver(schema),
    defaultValues: {
      title: initialValues?.title,
      category: initialValues?.category,
      content: initialValues?.content,
    },
  });

  console.log("inside form  ", initialValues);

  // eslint-disable-next-line @typescript-eslint/no-unused-expressions
  isSubmitSuccessful && reset();

  return (
    <form className={classes.form} onSubmit={handleSubmit(onSubmit)}>
      <div className={classes.field}>
        <label htmlFor="title">Title</label>
        <input id="title" type="text" {...register("title")} />
        {errors?.title && (
          <small className={classes.error_text}>{errors.title.message}</small>
        )}
      </div>

      <div className={classes.field}>
        <label htmlFor="category">Category</label>
        <input id="category" type="text" {...register("category")} />
        {errors?.category && (
          <small className={classes.error_text}>
            {errors.category.message}
          </small>
        )}
      </div>

      <div className={classes.field}>
        <label htmlFor="content">Content</label>
        <textarea {...register("content")} id="content"></textarea>
        {errors?.content && (
          <small className={classes.error_text}>{errors.content.message}</small>
        )}
      </div>
      <button>Create Blog Post</button>
    </form>
  );
};

export default BlogPostForm;
