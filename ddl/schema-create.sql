create table public.product_metadata (brand varchar(255), category varchar(255), product_id varchar(255) not null, primary key (product_id));
create table public.shopper (last_updated timestamp(6), shopper_id varchar(255) not null, primary key (shopper_id));
create table public.shopper_product_metadata (relevancy_score float(53), id bigint generated by default as identity, product_id varchar(255) not null, shopper_id varchar(255) not null, primary key (id), unique (shopper_id, product_id));
create index idx_category_id on public.product_metadata (category);
create index idx_brand_updated on public.product_metadata (brand);
alter table if exists public.shopper_product_metadata add constraint FKcvp6q2vym1os09jw10lyy1ndh foreign key (product_id) references public.product_metadata;
alter table if exists public.shopper_product_metadata add constraint FK1vkfg4ba6hgmwcd947rjt9k0c foreign key (shopper_id) references public.shopper;
